pipeline {
    agent any
    tools {
        maven 'maven3.8.5'
    }
    stages {
        stage('Build') {
            steps {
                echo 'Running build automation'
                sh "mvn clean install -Dmaven.test.skip"
                archiveArtifacts artifacts: 'target/*.jar'
            }
        }   
        stage('Build Docker Image') {
                when {
                    branch 'dev'
                }
                steps {
                    script {
                        app = docker.build("shobhan/docker-spring")
                    }
                }
        }
        stage('Push Docker Image') {
                when {
                    branch 'dev'
                }
                steps {
                    script {
                        docker.withRegistry('https://registry.hub.docker.com', 'dockerhub_login') {
                            app.push("${env.BUILD_NUMBER}")
                            app.push("latest")
                        }
                    }
                }
        }
    }  
}
