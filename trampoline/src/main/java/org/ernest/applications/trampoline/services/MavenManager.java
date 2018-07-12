package org.ernest.applications.trampoline.services;

import javax.annotation.PostConstruct;

import org.apache.maven.cli.MavenCli;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MavenManager {
	
	private final Logger log = LoggerFactory.getLogger(MavenManager.class);
    
	@PostConstruct
	public void init() {
		System.setProperty("maven.multiModuleProjectDirectory", "$M2_HOME");
	}
	
	public void copyMavenDependency(String groupId, String artifactId, String version, String destinationFolder) {
		String dependency = groupId+":"+ artifactId+":" + version;
		
		log.info("Copying maven dependency: [{}] destinationFolder: [{}]", dependency, destinationFolder);
	     
		String[] args = new String[] { "org.apache.maven.plugins:maven-dependency-plugin:3.1.1:copy", "-Dartifact=" + dependency,
				"-DoutputDirectory=.", "-Dmdep.useBaseVersion=true"};
	    MavenCli cli = new MavenCli();
	    cli.doMain(args, destinationFolder, System.out, System.err);
		
	}
	
}
