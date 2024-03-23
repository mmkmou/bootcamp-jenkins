pipeline {
    agent any

    environment {
      DOCKER_USER = credentials('docker_username')
      DOCKER_TOKEN = credentials('docker_token')
      SONAR_HOST = "http://159.65.211.146:9000"
      DEPLOYMENT_DIR = "deployment"
      DEPLOYMENT_REPO = "https://github.com/mmkmou/jenkins-k8s.git"
      APPLICATION_NAME = "transactions"
    }

    tools {
      git 'Git'
      maven 'maven-3.9.6'
    }

    stages {
        stage('SCM Checkout') {
            steps {

                echo "---------------------- Checkout Main ------------------------------------------------"
                // Get some code from a GitHub repository
                echo "checked out ${env.BRANCH_NAME} - ${env.TAG_NAME}"

            }
        }

        /*stage('Code Quality Check') {
            steps {

                echo "---------------------- Code QualityMain ------------------------------------------------"
                echo "We will run sonarQube here"
                withCredentials([string(credentialsId: 'sonar_token', variable: 'SONARQUBETOKEN')]) {
					sh "mvn sonar:sonar -Dsonar.login=$SONARQUBETOKEN -Dsonar.qualitygate.wait=true -Dsonar.host.url=${env.SONAR_HOST}"
				}
            }
        }*/

        stage('Build') {
            steps {
                echo "----------------------------Build with Maven------------------------------------------"
                // Run Maven on a Unix agent.
                sh 'mvn clean verify -Dmaven.test.skip=true'
            }
        }

        stage('Build docker tag') {
            when {
                tag "v*"
            }

            steps {
                script {
                    echo "----------------------------Build docker------------------------------------------"
                    def dockerImage = "${env.DOCKER_USER}/transactions:${env.TAG_NAME}"
                    sh "docker build -f Dockerfile -t ${dockerImage} ."
                    sh "echo ${env.DOCKER_TOKEN} | docker login --username ${env.DOCKER_USER} --password-stdin"
                    sh "docker push ${dockerImage}"

                    echo "---- Im here -----"
                    def deploymentDir =  ${env.DEPLOYMENT_DIR}
                    if (!fileExists(deploymentDir)) {
                        echo "---- inside check file fileExists here -----"
                        sh "git clone ${env.DEPLOYMENT_REPO} ${deploymentDir}"
                    }

                    echo "---- now here -----"
                    dir(deploymentDir){
                        sh "git pull origin master"
                        def deploymentFilePath = "main/deployment.yaml"
                        def deploymentContent = readFile(deploymentFilePath).trim()
                        def updatedContent = deploymentContent.replaceAll(/image:.*transactions.*/, "image: ${dockerImage}")
                        writeFile(file: deploymentFilePath, text: updatedContent)
                    }

                    // push to baobab-charts repo for argocd automatic/manuel deployment
                    sh """
                        git add main/deployment.yaml
                        git commit -m "Update image tag for release to ${dockerImage}"
                        git push origin master
                    """
                }

            }
        }

        stage('Build docker release') {
            when {
              branch "release-*"
            }

            steps {
                script {
                    def dockerImage = "${env.DOCKER_USER}/transactions:${env.BRANCH_NAME}"
                    echo "----------------------------Build docker------------------------------------------"
                    sh """
                      docker build -f Dockerfile -t ${dockerImage} .
                      echo ${env.DOCKER_TOKEN} | docker login --username ${env.DOCKER_USER} --password-stdin
                      docker push ${dockerImage}
                    """
                     echo "---- Im here -----"
                    def deploymentDir =  ${env.DEPLOYMENT_DIR}
                    if (!fileExists(deploymentDir)) {
                        echo "---- inside check file fileExists here -----"
                        sh "git clone ${env.DEPLOYMENT_REPO} ${deploymentDir}"
                    }

                    echo "---- now here -----"
                    dir(deploymentDir){
                        sh "git pull origin master"
                        def deploymentFilePath = "release/deployment.yaml"
                        def deploymentContent = readFile(deploymentFilePath).trim()
                        def updatedContent = deploymentContent.replaceAll(/image:.*transactions.*/, "image: ${dockerImage}")
                        writeFile(file: deploymentFilePath, text: updatedContent)
                    }

                    // push to baobab-charts repo for argocd automatic/manuel deployment
                    sh """
                        git add release/deployment.yaml
                        git commit -m "Update image tag for release to ${dockerImage}"
                        git push origin master
                    """
                }

            }
        }
    }

    post {
        // If Maven was able to run the tests, even if some of the test
        // failed, record the test results and archive the jar file.
        success {
            echo "${BUILD_ID}"
            archiveArtifacts 'target/*.jar'

        }
    }
}
