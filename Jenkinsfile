pipeline {
    agent any
    tools {
        maven 'maven3.8.5'
    }
    stages {
        stage('Build') {
            steps {
                echo 'Running build automation'
                sh "mvn clean install"
                archiveArtifacts artifacts: 'target/*.jar'
            }
        }
    }
}
