def call(String imageRepoName, String awsAccountId, String imageTag, String awsDefaultregion) {
    
    sh """
     trivy image ${awsAccountId}.dkr.ecr.${awsDefaultregion}.amazonaws.com/${imageRepoName}:${imageTag} > scan.txt
     cat scan.txt
    """ 
}