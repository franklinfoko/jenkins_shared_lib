def call(String imageRepoName, String imageTag, String hubUser, String awsDefaultregion, String awsAccountId) {
    withCredentials([usernamePassword(
        credentialsId: 'dockerhub',
        passwordVariable: 'DOCKERHUB_PASSWORD',
        usernameVariable: 'DOCKERHUB_USER'
    )]) {
        sh "docker login -u '$DOCKERHUB_USER' -p '$DOCKERHUB_PASSWORD'"
    }
    sh "docker push ${hubUser}/${imageRepoName}:${ImageTag}"

    withCredentials([<object of type com.cloudbees.jenkins.plugins.awscredentials.AmazonWebServicesCredentialsBinding>]) {
        sh "aws ecr get-login-password --region ${awsDefaultregion} | docker login --username AWS --password-stdin ${awsAccountId}.dkr.ecr.${awsDefaultregion}.amazonaws.com"
}
    sh "docker push ${awsAccountId}.dkr.ecr.${awsDefaultregion}.amazonaws.com/${imageRepoName}:${imageTag}"
}