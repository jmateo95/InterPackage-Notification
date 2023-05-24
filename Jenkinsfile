pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "maven_3_6_3"
    }

    stages {
        // stage('TEST Y JAR DEV') {
            steps {
                checkout scmGit(branches: [[name: '*/dev']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/jmateo95/InterPackage-Notification']])
                sh 'ssh root@137.184.209.89 "cd /home/Interpackage/InterPackage-Notification && git pull origin dev && mvn clean install"'
            }
        }

        // stage('Detener El Servicio Docker') {
        //     steps {
        //         sh 'ssh root@137.184.209.89 "docker stop interpackage-docker-interpackage-service-notification-1"'
        //         sh 'ssh root@137.184.209.89 "docker rm interpackage-docker-interpackage-service-notification-1"'
        //     }
        // }

        // stage ('Desplegar Imgen de Docker'){
        //     steps{
        //         script{
        //             sh 'ssh root@137.184.209.89 "cd /home/Interpackage/InterPackage-Docker && docker-compose up -d --build interpackage-service-notification"'
        //         }
        //     }  
        // }


        stage('Merge a Main') {
            steps {
                sh 'ssh root@164.90.232.216 "cd /home/Interpackage/InterPackage-Notification && git checkout main && git merge origin/dev && git push origin main"'
            }
        }

        stage('Jar en Produccion') {
            steps {
                sh 'ssh root@164.90.232.216 "cd /home/Interpackage/InterPackage-Notification && mvn clean install -DskipTests"'
            }
        }

        stage('Detener en Produccion') {
            steps {
                sh 'ssh root@164.90.232.216 "docker stop interpackage-docker-interpackage-service-notification-1"'
                sh 'ssh root@164.90.232.216 "docker rm interpackage-docker-interpackage-service-notification-1"'
            }
        }

        stage ('Desplegar en Produccion'){
            steps{
                script{
                    sh 'ssh root@164.90.232.216 "cd /home/Interpackage/InterPackage-Docker && docker-compose up -d --build interpackage-service-notification"'
                }
            }  
        }


    }
}