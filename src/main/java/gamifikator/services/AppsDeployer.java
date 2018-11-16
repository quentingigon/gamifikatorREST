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
     * @param pathToApp path to app to deploy
     */
    public void deployUserApp(String pathToApp){

        //Deploy the application on the payara container
        folderSrc = new File(pathToApp);
        try {
            String path = folderSrc.getAbsolutePath().replace("\\", "/");
            pr = rt.exec("docker cp " + path + " docker_payara_1:/opt/payara/glassfish/domains/domain1/autodeploy");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
	 * Deploy all the applications of the user username
     * @param pathToUserFolder The path to user folder of apps
     */
    public void deployAllUserApps(String pathToUserFolder){
        folderSrc = new File(pathToUserFolder);
        for(File appToDeploy : folderSrc.listFiles()) {
            deployUserApp(pathToUserFolder + "/" + appToDeploy.getName());
        }
    }

    /**
     *  Deploy all the applications of all users
     */
    public void deployAllApps(String path) {
        folderSrc = new File(path);
        for(File user : folderSrc.listFiles()){
            deployAllUserApps(path + "/" + user.getName());
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
     * @param pathToUserFolder A String array containing the path to user folder
     */
    public void undeployAllUserApps(String pathToUserFolder) {
		folderSrc = new File(pathToUserFolder);
		for(File app : folderSrc.listFiles()){
			undeployUserApp(pathToUserFolder + "/" + app.getName());
		}
    }

}


