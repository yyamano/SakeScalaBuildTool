package sake.environment

import sake.util.Path

/**
 * Encapsulates runtime environment properties, etc. Some java.lang.System
 * properties are exposed as variables for convenient use in build scripts.
 */
class Environment {

    /**
     * If true, don't actually build anything, just report the the commands that would be executed.
     */
    var dryRun = false
    
    /**
     * For convenience, the path separator is exposed explicitly, read only.
     */
    val pathSeparator = Environment.getSystemProperty("path.separator")
    
    /**
     * For convenience, the file separator is exposed explicitly, read only.
     */
    val fileSeparator = Environment.getSystemProperty("file.separator")
    
    /**
     * For convenience, the line separator is exposed explicitly, read only.
     */
    val lineSeparator = Environment.getSystemProperty("line.separator")
    
    /**
     * For convenience, the current working directory is exposed explicitly, read only.
     */
    val currentWorkingDirectory = Environment.getSystemProperty("user.dir")
    
    /**
     * For convenience, the environment variables map is exposed explicitly, read only.
     */
    val environmentVariables = Environment.getSystemEnvironmentVariables
    
    /**
     * For convenience and to keep the system classpath consistent with any user changes,
     * the "classpath" is exposed explicitly as a List that is kept synchronized with the
     * system's value. Use standard list operations to change it. 
     */
    def classpath: Path = {
        val seq = for {
            s <- Environment.getSystemProperty("java.class.path").split(pathSeparator)
        } yield s
        Path(seq)(pathSeparator)
    }

    /**
     * Set the system classpath.
     */
    def classpath_=(newPath: Path) = {
        Environment.setSystemProperty("java.class.path", newPath.joined)
    }
}

object Environment {
    
    val environment:Environment = new Environment()

    /**
     * Returns a system property or "" if not defined. If you would prefer null for
     * not defined, use System.getProperty() directory.
     */
    def getSystemProperty(key:String) = System.getProperty(key) match {
        case null => ""
        case s    => s
    }

    def setSystemProperty(key:String, value:String):Unit = System.setProperty(key, value)
    
    /**
     * Returns the system environment, a map of variables.
     */
    def getSystemEnvironmentVariables = System.getenv()
}