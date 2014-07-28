/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jboss.jdf.stacks.client;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 * @author <a href="mailto:benevides@redhat.com">Rafael Benevides</a>
 * @author <a href="mailto:jperkins@redhat.com">James R. Perkins</a>
 * 
 */
public class DefaultStacksClientConfiguration implements StacksClientConfiguration {

    private URL url;

    private String proxyHost;

    private int proxyPort;

    private String proxyUser;

    private String proxyPassword;

    private boolean online = true;

    private int cacheRefreshPeriodSeconds = 86400;

    /*
     * (non-Javadoc)
     * 
     * @see org.jboss.jdf.stacks.client.StacksClientConfiguration#getUrl()
     */
    @Override
    public URL getUrl() {
        String repo = null;
        if (url == null) {
            try {
                repo = System.getProperty(REPO_PROPERTY);
                if (repo == null) {
                    repo = getPropertyFromConfig();
                }
                return new URL(repo);
            } catch (Exception e) {
                throw new IllegalStateException(String.format("Could not create URL from '%s'", repo));
            }
        } else {
            return url;
        }
    }

    /**
     * @return
     * @throws IOException
     */
    private String getPropertyFromConfig() throws IOException {
        InputStream is = null;
        try {
            is = this.getClass().getResourceAsStream("/org/jboss/jdf/stacks/client/config.properties");
            Properties p = new Properties();
            p.load(is);
            return p.getProperty(REPO_PROPERTY);
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jboss.jdf.stacks.client.StacksClientConfiguration#getProxyHost()
     */
    @Override
    public String getProxyHost() {
        return proxyHost;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jboss.jdf.stacks.client.StacksClientConfiguration#getProxyPort()
     */
    @Override
    public int getProxyPort() {
        return proxyPort;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jboss.jdf.stacks.client.StacksClientConfiguration#getProxyUser()
     */
    @Override
    public String getProxyUser() {
        return proxyUser;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jboss.jdf.stacks.client.StacksClientConfiguration#getProxyPassword()
     */
    @Override
    public String getProxyPassword() {
        return proxyPassword;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jboss.jdf.stacks.client.StacksClientConfiguration#setUrl(java.net.URL)
     */
    @Override
    public void setUrl(URL url) {
        this.url = url;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jboss.jdf.stacks.client.StacksClientConfiguration#setProxyHost(java.lang.String)
     */
    @Override
    public void setProxyHost(String proxyHost) {
        this.proxyHost = proxyHost;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jboss.jdf.stacks.client.StacksClientConfiguration#setProxyPort(int)
     */
    @Override
    public void setProxyPort(int proxyPort) {
        this.proxyPort = proxyPort;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jboss.jdf.stacks.client.StacksClientConfiguration#setProxyUser(java.lang.String)
     */
    @Override
    public void setProxyUser(String proxyUser) {
        this.proxyUser = proxyUser;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jboss.jdf.stacks.client.StacksClientConfiguration#setProxyPassword(java.lang.String)
     */
    @Override
    public void setProxyPassword(String proxyPassword) {
        this.proxyPassword = proxyPassword;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jboss.jdf.stacks.client.StacksClientConfiguration#isOnline()
     */
    @Override
    public boolean isOnline() {
        return online;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jboss.jdf.stacks.client.StacksClientConfiguration#setOnline(boolean)
     */
    @Override
    public void setOnline(boolean online) {
        this.online = online;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jboss.jdf.stacks.client.StacksClientConfiguration#getCacheRefreshPeriodoInSeconds()
     */
    @Override
    public int getCacheRefreshPeriodInSeconds() {
        return cacheRefreshPeriodSeconds;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jboss.jdf.stacks.client.StacksClientConfiguration#setCacheRefreshPeriodInSeconds()
     */
    @Override
    public void setCacheRefreshPeriodInSeconds(int seconds) {
        this.cacheRefreshPeriodSeconds = seconds;

    }

}
