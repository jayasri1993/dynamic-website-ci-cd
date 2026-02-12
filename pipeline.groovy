pipeline {
    agent any

    stages {
        stage('Git Integration') {
            steps {
                git 'https://github.com/jayasri1993/dynamic-website-ci-cd.git'
            }
        }
        stage('Send bulid artifacts over SSH') {
            steps {
                sshPublisher(publishers: [sshPublisherDesc(configName: 'linuxserver1', transfers: [sshTransfer(cleanRemote: false, excludes: '', execCommand: '', execTimeout: 120000, flatten: false, makeEmptyDirs: false, noDefaultExcludes: false, patternSeparator: '[, ]+', remoteDirectory: 'www/html', remoteDirectorySDF: false, removePrefix: '', sourceFiles: '**/*.*')], usePromotionTimestamp: false, useWorkspaceInPromotion: false, verbose: false)])
            }
        }
        stage('Archive the artifacts') {
            steps {
                archiveArtifacts artifacts: '**/*.*', followSymlinks: false
            }
        }
    }
}