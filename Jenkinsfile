pipeline {
    agent any
    tools {
        maven 'maven3.8.5'
    }
    stages {
        stage('Build') {
            steps {
                sh "mvn clean install"
                echo 'Running build automation'    
                archiveArtifacts artifacts: 'target/*.jar'
            }
        }
    }
}
