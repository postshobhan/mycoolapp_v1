pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                echo 'Running build automation'
                withMaven {
                  sh "mvn clean install"
                }
                archiveArtifacts artifacts: 'target/*.jar'
            }
        }
    }
}
