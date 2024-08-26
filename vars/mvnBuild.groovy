def call() {
    try {
        sh 'mvn clean install'
    } catch ( Exception e) {
        println "Error: ${e.message}"
    }
}
