def call(String imageRepoName, String awsAccountId, String imageTag, String awsDefaultregion, String hubUser) {
    
    sh """
     docker build -t ${imageRepoName}:${imageTag} .
     docker tag ${imageRepoName}:${imageTag} ${ubUser}/${imageRepoName}:${ImageTag}
     docker tag ${imageRepoName}:${imageTag} ${awsAccountId}.dkr.ecr.${awsDefaultregion}.amazonaws.com/${imageRepoName}:${imageTag}
    """
}