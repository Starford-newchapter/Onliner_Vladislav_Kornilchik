pipeline {
    agent any

    tools{
       maven "MAVEN"
    }
    environment{
        SUITE='src/test/resources/${params.Suite}.xml'
    }

    stages {
        stage('Build') {
            steps {
                bat 'mvn clean'
            }
        }
    stage('Test run'){
        steps{
            bat 'mvn clean test -Dsuite=${env.SUITE}'
        }
    }
                stage('Reports') {
                    steps {
                        script {
                            allure([
                                includeProperties: false,
                                jdk: '',
                                properties: [],
                                reportBuildPolicy: 'ALWAYS',
                                results: [[path: 'target/allure-results']]
                            ])
                        }
                    }
                }
            }
        }