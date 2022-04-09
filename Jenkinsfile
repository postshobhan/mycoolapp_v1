pipeline {
    agent any
    stages {
        stage('Build') {
            withMaven {
                sh "mvn clean install"
            }
            steps {
                echo 'Running build automation'    
                archiveArtifacts artifacts: 'target/*.jar'
            }
        }
    }
}
