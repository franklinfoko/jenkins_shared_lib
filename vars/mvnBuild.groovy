def call() {
    try {
        sh 'mvn clean install'
    } catch(Exception e) {
        println "Fail: ${e.message}"
    }
}
