/*Copy the apps to deploy in the payara container directory
to deploy them automatically

Usage:
    Run cmd 'docker-compose up --build' to start paraya
    Copy the uploaded app to deploy in the appToDeploy directory
    Run the script deployApp.js to deploy the apps
*/

const fs = require('fs');
var cmd = require('node-cmd');

const pathSrc = '../appToDeploy'; 
var absolutePath;


const pathSrc2 = 'C:/Users/Cloud/Documents/Git/AMT/gamifikator/appToDeploy'   

fs.readdir(pathSrc, function(err, items) {
   
    //For each app to deploy
    for (var i=0; i<items.length; i++) {

        absolutePath = __dirname + '\\..\\appToDeploy\\' + items[i];
        absolutePath = absolutePath.replace(/\\/g, '/');
        cmd.run('docker cp ' + absolutePath + ' topologyamt_payara_1:/opt/payara/glassfish/domains/domain1/autodeploy');
    }
});
