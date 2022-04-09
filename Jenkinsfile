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
        stage('DeployToStaging') {
            when {
                branch 'dev'
            }
            steps {
                milestone(1)
                sshagent(credentials : ['ssh_key_staging']) {
                    sh "ssh -o StrictHostKeyChecking=no cloud_user@$staging_ip \"docker pull shobhan/docker-spring:${env.BUILD_NUMBER}\""
                    sh 'ssh -v cloud_user@$staging_ip'
                }
                withCredentials([usernamePassword(credentialsId: 'webserver_login', usernameVariable: 'USERNAME', passwordVariable: 'USERPASS')]) {
                    script {
                        sh "sshpass -p '$USERPASS' -v ssh -o StrictHostKeyChecking=no $USERNAME@$staging_ip \"docker pull shobhan/docker-spring:${env.BUILD_NUMBER}\""
                        try {
                            sh "sshpass -p '$USERPASS' -v ssh -o StrictHostKeyChecking=no $USERNAME@$staging_ip \"docker stop docker-spring\""
                            sh "sshpass -p '$USERPASS' -v ssh -o StrictHostKeyChecking=no $USERNAME@$staging_ip \"docker rm docker-spring\""
                        } catch (err) {
                            echo: 'caught error: $err'
                        }
                        sh "sshpass -p '$USERPASS' -v ssh -o StrictHostKeyChecking=no $USERNAME@$staging_ip \"docker run --restart always --name docker-spring -p 8080:8080 -d shobhan/docker-spring:${env.BUILD_NUMBER}\""
                    }
                }
            }
        }   
    }
}
