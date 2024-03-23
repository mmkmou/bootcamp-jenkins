pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "mvn-3.9.6"
    }

    stages {

        stage('SCM checkout') {
            steps {
                echo "--------------- Checkout -------------------"
                echo "${env.BRANCH_NAME} - ${env.JOB_NAME} - ${env.BUILD_ID}"
            }

        }

        stage('Build') {
            steps {

                // Run Maven on a Unix agent.
                sh "mvn -Dmaven.test.skip=true clean package"

                // To run Maven on a Windows agent, use
                // bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }

            post {
                // If Maven was able to run the tests, even if some of the test
                // failed, record the test results and archive the jar file.
                success {
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
    }
}