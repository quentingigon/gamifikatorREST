/*Copy the apps to deploy in the payara container directory
to deploy them automatically

Usage:
    Run cmd 'docker-compose up --build' to start paraya and the server
    Copy the uploaded app to deploy in the appToDeploy directory
    Run the script deployApp.js to deploy the apps
*/

const fs = require('fs');
var cmd = require('node-cmd');
const pathSrc = '../appToDeploy'; 
const pathSrc2 = 'C:/Users/Cloud/Documents/Git/AMT/gamifikator/appToDeploy'   


fs.readdir(pathSrc, function(err, items) {
   
    //For each app to deploy
    for (var i=0; i<items.length; i++) {

        console.log(items[i]); //absolute path? Seems to be only filename

        //node-cmd doesn't know docker? Doesn't work on classic cmd either
        cmd.get(
            'docker cp ' + pathSrc2 + '/' + items[i] + ' topologyamt_payara_1:/.',
            function(err, data, stderr){
                console.log(data)
            }
        );
        //cmd.run('docker cp ' + items[i] + 'C:/Users/Cloud/Documents/Git/AMT/gamifikator');
        //cmd.run('docker cp ' + items[i] + ' topologyamt_payara_1:/$GLASSFISH_HOME/glassfish/domains/domain1/autodeploy');
    }
});

