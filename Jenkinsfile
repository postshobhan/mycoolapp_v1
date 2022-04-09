pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                withMaven {
                    sh "mvn clean install"
                }
                echo 'Running build automation'    
                archiveArtifacts artifacts: 'target/*.jar'
            }
        }
    }
}
