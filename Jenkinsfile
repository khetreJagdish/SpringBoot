def workspace
node{
    
    stage("Checkout"){
        checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/khetreJagdish/SpringBoot.git']]])
        workspace = pwd()
        
    }
    
    stage("Triggred Build"){
        echo "Triggring Build Now"
    }
    
    stage("Deployement"){
        echo "Deployement"
    }
    
    stage("Unit Testing"){
        echo "Unit Testing"
    }
    
    stage("Delivery"){
        echo "Delivery"
    }
}
