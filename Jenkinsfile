pipeline {
    agent any

    environment {
      DOCKER_USER = credentials('docker_username')
      DOCKER_TOKEN = credentials('docker_token')
      SONAR_HOST = "http://159.65.211.146:9000"
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

        stage('Build docker main') {
            when {
                tag "v*"
            }

            steps {
                echo "----------------------------Build docker------------------------------------------"
                sh "docker build -f Dockerfile -t ${env.DOCKER_USER}/transactions ."
                sh "docker tag ${env.DOCKER_USER}/transactions ${env.DOCKER_USER}/transactions:${env.TAG_NAME}"
                sh "echo ${env.DOCKER_TOKEN} | docker login --username ${env.DOCKER_USER} --password-stdin"
                sh "docker push ${env.DOCKER_USER}/transactions:${env.TAG_NAME}"
                sh "docker push ${env.DOCKER_USER}/transactions"
            }
        }

        stage('Build docker release') {
            when {
              branch "release-*"
            }

            steps {
                echo "----------------------------Build docker------------------------------------------"
                sh "docker build -f Dockerfile -t ${env.DOCKER_USER}/transactions:${env.BRANCH_NAME} ."
                sh "echo ${env.DOCKER_TOKEN} | docker login --username ${env.DOCKER_USER} --password-stdin"
                sh "docker push ${env.DOCKER_USER}/transactions:${env.BRANCH_NAME}"
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