def call(String imageRepoName, String awsAccountId, String imageTag, String awsDefaultregion, String hubUser) {
    withCredentials([usernamePassword(
        credentialsId: 'dockerhub',
        passwordVariable: 'DOCKERHUB_PASSWORD',
        usernameVariable: 'DOCKERHUB_USER'
    )]) {
        sh "docker login -u '$DOCKERHUB_USER' -p '$DOCKERHUB_PASSWORD'"
    }
    sh "docker push ${ubUser}/${imageRepoName}:${ImageTag}"
}