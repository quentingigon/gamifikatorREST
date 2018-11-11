/*Copy the apps to deploy in the payara container directory
to deploy them automatically

Usage:
    Run cmd 'docker-compose up --build' to start paraya
    Copy the uploaded app to deploy in the appToDeploy directory
    Run the script deployApp.js to deploy the apps
*/

const fs = require('fs');
var cmd = require('node-cmd');

const pathSrc = '../appsToDeploy'; 


fs.readdir(pathSrc, function(err, items) {
   
    var absolutePath;

    //For each app to deploy
    for (var i=0; i<items.length; i++) {

        absolutePath = __dirname + '\\..\\appsToDeploy\\' + items[i];
        absolutePath = absolutePath.replace(/\\/g, '/');
        cmd.run('docker cp ' + absolutePath + ' topologyamt_payara_1:/opt/payara/glassfish/domains/domain1/autodeploy');
    }
});
