package org.apache.maven.plugin.assembly;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.assembly.archiver.DirectoryArchiver;
import org.apache.maven.plugins.assembly.model.Assembly;
import org.codehaus.plexus.archiver.Archiver;
import org.codehaus.plexus.archiver.ArchiverException;

/**
 * Assemble an application bundle or distribution.
 *
 * @goal directory
 * @requiresDependencyResolution test
 * @requiresDirectInvocation
 * @execute phase="package"
 */
public class DirectoryMojo
    extends AssemblyMojo
{
    public void execute()
        throws MojoExecutionException, MojoFailureException
    {
        List assemblies = readAssemblies();
        for ( Iterator i = assemblies.iterator(); i.hasNext(); )
        {
            Assembly assembly = (Assembly) i.next();
            createDirectory( assembly );
        }
    }

    private void createDirectory( Assembly assembly )
        throws MojoExecutionException, MojoFailureException
    {

        String fullName = finalName + "-" + assembly.getId();

        try
        {
            Archiver archiver = new DirectoryArchiver();

            createArchive( archiver, assembly, fullName );
        }
        catch ( ArchiverException e )
        {
            throw new MojoExecutionException( "Error creating assembly", e );
        }
        catch ( IOException e )
        {
            throw new MojoExecutionException( "Error creating assembly", e );
        }
    }

}
