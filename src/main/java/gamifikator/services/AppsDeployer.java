package gamifikator.services;

import java.io.File;
import java.io.IOException;

public class AppsDeployer {


    private File folderSrc;                 //The folder containing the apps to deploy

    //Used to process shell commands
    private Runtime rt;
    private Process pr;

    public AppsDeployer(){
         rt = Runtime.getRuntime();
    }

    //------------------Deploy apps-------------------------

    /**
	 * Deploy a specific application of the user username
     * @param username The username of the user willing to deploy an app
     * @param appToDeploy The application's name to deploy
     */
    public void deployUserApp(String username, String appToDeploy){

        if(!appToDeploy.matches("([a-zA-Z0-9\\s_\\\\.\\-\\(\\):])+(.war|.WAR)$")){
            appToDeploy += ".war";
        }

        //Deploy the application on the payara container
        folderSrc = new File("appsToDeploy/" + username + "/" + appToDeploy);
        try {
            String path = folderSrc.getAbsolutePath().replace("\\", "/");
            pr = rt.exec("docker cp " + path + " docker_payara_1:/opt/payara/glassfish/domains/domain1/autodeploy");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
	 * Deploy all the applications of the user username
     * @param username The username of the user willing to deploy an app
     */
    public void deployAllUserApps(String username){
        folderSrc = new File("appsToDeploy/" + username);
        for(File appToDeploy : folderSrc.listFiles()) {
            deployUserApp(username, appToDeploy.getName());
        }
    }

    /**
     *  Deploy all the applications of all users
     */
    public void deployAllApps(){
        folderSrc = new File("appsToDeploy");
        for(File user : folderSrc.listFiles()){
            deployAllUserApps(user.getName());
        }
    }

    //------------------Undeploy apps------------------------

    /**
	 * Undeploy a specific application of the user username
     * @param appToUndeploy The application's name to undeploy
     */
    public void undeployUserApp(String appToUndeploy){

        if(!appToUndeploy.matches("([a-zA-Z0-9\\s_\\\\.\\-\\(\\):])+(.war|.WAR)$")){
            appToUndeploy += ".war";
        }

        try {
            pr = rt.exec("docker exec docker_payara_1 rm -rf /opt/payara/glassfish/domains/domain1/autodeploy/" + appToUndeploy);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
	 * Undeploy all the applications given in the array paramter
     * @param appsToUndeploy A String array containing all the apps name to undeploy
     */
    public void undeployAllUserApps(String[] appsToUndeploy){
        for(String app : appsToUndeploy){
            undeployUserApp(app);
        }
    }

}


