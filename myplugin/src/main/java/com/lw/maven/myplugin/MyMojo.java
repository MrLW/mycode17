package com.lw.maven.myplugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;

/**
 * 
 * @author liwen
 * @date:2017年11月24日 下午4:38:19
 * @Function:
 * @version 1.0
 */
@Mojo(name="hello")
public class MyMojo extends AbstractMojo {

	public void execute() throws MojoExecutionException, MojoFailureException {
		System.out.println("hello mojo");
	}

}
