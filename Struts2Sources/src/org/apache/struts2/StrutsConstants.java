/*
 * $Id: StrutsConstants.java 789432 2009-06-29 20:15:24Z musachy $
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.struts2;

import org.apache.struts2.dispatcher.mapper.CompositeActionMapper;

/**
 * This class provides a central location for framework configuration keys
 * used to retrieve and store Struts configuration settings.
 * 该类提供了所有很多Struts框架中配置文件的key，常被用于获取或存储Struts相关设置信息
 */
public final class StrutsConstants {

    /** Struts是否是开发模式 */
    public static final String STRUTS_DEVMODE = "struts.devMode";

    /** 本地化信息是否应该被自动加载 */
    public static final String STRUTS_I18N_RELOAD = "struts.i18n.reload";

    /** 本地化信息使用的编码 */
    public static final String STRUTS_I18N_ENCODING = "struts.i18n.encoding";

    /** 是否重新加载XML配置文件 */
    public static final String STRUTS_CONFIGURATION_XML_RELOAD = "struts.configuration.xml.reload";

    /** URL扩展名，用于决定request请求是否为Strtus action */
    public static final String STRUTS_ACTION_EXTENSION = "struts.action.extension";

    /** 使用逗号隔开的一个正则表达式列表(java.util.regex.Pattern)，用于排除一些Struts2处理请求 */
	public static final String STRUTS_ACTION_EXCLUDE_PATTERN = "struts.action.excludePattern";

    /** Whether to use the alterative syntax for the tags or not */
    public static final String STRUTS_TAG_ALTSYNTAX = "struts.tag.altSyntax";

    /** The HTTP port used by Struts URLs，HTTP端口用于Struts URLs */
    public static final String STRUTS_URL_HTTP_PORT = "struts.url.http.port";

    /** The HTTPS port used by Struts URLs，HTTPS端口用于Struts URLs */
    public static final String STRUTS_URL_HTTPS_PORT = "struts.url.https.port";

    /** The default includeParams method to generate Struts URLs */
    public static final String STRUTS_URL_INCLUDEPARAMS = "struts.url.includeParams";

	public static final String STRUTS_URL_RENDERER = "struts.urlRenderer";

    /** The com.opensymphony.xwork2.ObjectFactory 的实现类 */
    public static final String STRUTS_OBJECTFACTORY = "struts.objectFactory";

    /** The com.opensymphony.xwork2.util.ObjectTypeDeterminer implementation class */
    public static final String STRUTS_OBJECTTYPEDETERMINER = "struts.objectTypeDeterminer";

    /** The package containing actions that use Rife continuations */
    public static final String STRUTS_CONTINUATIONS_PACKAGE = "struts.continuations.package";

    /** The org.apache.struts2.config.Configuration 的实现类 */ 
    public static final String STRUTS_CONFIGURATION = "struts.configuration";

    /** The default locale for the Struts application */
    public static final String STRUTS_LOCALE = "struts.locale";

    /** Whether to use a Servlet request parameter workaround necessary for some versions of WebLogic */
    public static final String STRUTS_DISPATCHER_PARAMETERSWORKAROUND = "struts.dispatcher.parametersWorkaround";

    /** The org.apache.struts2.views.freemarker.FreemarkerManager 的实现类 */
    public static final String STRUTS_FREEMARKER_MANAGER_CLASSNAME = "struts.freemarker.manager.classname";
    
    /** Cache Freemarker templates */
    public static final String STRUTS_FREEMARKER_TEMPLATES_CACHE = "struts.freemarker.templatesCache";
    
    /** Cache model instances at BeanWrapper level */
    public static final String STRUTS_FREEMARKER_BEANWRAPPER_CACHE = "struts.freemarker.beanwrapperCache";
    
    /** Maximum strong sizing for MruCacheStorage for freemarker */
    public static final String STRUTS_FREEMARKER_MRU_MAX_STRONG_SIZE = "struts.freemarker.mru.max.strong.size";
    
    /** org.apache.struts2.views.velocity.VelocityManager implementation class */
    public static final String STRUTS_VELOCITY_MANAGER_CLASSNAME = "struts.velocity.manager.classname";

    /** The Velocity configuration file path */
    public static final String STRUTS_VELOCITY_CONFIGFILE = "struts.velocity.configfile";

    /** The location of the Velocity toolbox */
    public static final String STRUTS_VELOCITY_TOOLBOXLOCATION = "struts.velocity.toolboxlocation";

    /** List of Velocity context names */
    public static final String STRUTS_VELOCITY_CONTEXTS = "struts.velocity.contexts";

    /** The directory containing UI templates.  All templates must reside in this directory. */
    /** 该目录包含UI templates.  所有templates必须都在该目录里. */
    public static final String STRUTS_UI_TEMPLATEDIR = "struts.ui.templateDir";

    /** 默认UI模板主题 */
    public static final String STRUTS_UI_THEME = "struts.ui.theme";

    /** multipart请求的最大字节数 (文件上传) */
    public static final String STRUTS_MULTIPART_MAXSIZE = "struts.multipart.maxSize";

    /** 该目录用于存储上传文件 */
    public static final String STRUTS_MULTIPART_SAVEDIR = "struts.multipart.saveDir";

    /**
     * The name of the bean that will handle multipart requests（将被用于处于文件上传请求的bean的名称）
     */
    public static final String STRUTS_MULTIPART_HANDLER = "struts.multipart.handler";

    /**
     * The org.apache.struts2.dispatcher.multipart.MultiPartRequest parser implementation
     * for a multipart request (文件上传)
     */
    public static final String STRUTS_MULTIPART_PARSER = "struts.multipart.parser";

    /** How Spring should autowire.  Valid values are 'name', 'type', 'auto', and 'constructor' */
    public static final String STRUTS_OBJECTFACTORY_SPRING_AUTOWIRE = "struts.objectFactory.spring.autoWire";

    /** Whether the autowire strategy chosen by STRUTS_OBJECTFACTORY_SPRING_AUTOWIRE is always respected.  Defaults
     * to false, which is the legacy behavior that tries to determine the best strategy for the situation.
     * @since 2.1.3
     */
    public static final String STRUTS_OBJECTFACTORY_SPRING_AUTOWIRE_ALWAYS_RESPECT = "struts.objectFactory.spring.autoWire.alwaysRespect";

    /** Whether Spring should use its class cache or not */
    public static final String STRUTS_OBJECTFACTORY_SPRING_USE_CLASS_CACHE = "struts.objectFactory.spring.useClassCache";

    /** Whether or not XSLT templates should not be cached */
    public static final String STRUTS_XSLT_NOCACHE = "struts.xslt.nocache";

    /** Location of additional configuration properties files to load */
    public static final String STRUTS_CUSTOM_PROPERTIES = "struts.custom.properties";

    /** Location of additional localization properties files to load */
    public static final String STRUTS_CUSTOM_I18N_RESOURCES = "struts.custom.i18n.resources";

    /** The org.apache.struts2.dispatcher.mapper.ActionMapper implementation class */
    public static final String STRUTS_MAPPER_CLASS = "struts.mapper.class";

    /** Whether the Struts filter should serve static content or not */
    public static final String STRUTS_SERVE_STATIC_CONTENT = "struts.serve.static";

    /** If static content served by the Struts filter should set browser caching header properties or not */
    public static final String STRUTS_SERVE_STATIC_BROWSER_CACHE = "struts.serve.static.browserCache";

    /** Allows one to disable dynamic method invocation from the URL */
    public static final String STRUTS_ENABLE_DYNAMIC_METHOD_INVOCATION = "struts.enable.DynamicMethodInvocation";

    /** Whether slashes in action names are allowed or not */
    public static final String STRUTS_ENABLE_SLASHES_IN_ACTION_NAMES = "struts.enable.SlashesInActionNames";

    /** Prefix used by {@link CompositeActionMapper} to identify its containing {@link org.apache.struts2.dispatcher.mapper.ActionMapper} class. */
    public static final String STRUTS_MAPPER_COMPOSITE = "struts.mapper.composite";

    public static final String STRUTS_ACTIONPROXYFACTORY = "struts.actionProxyFactory";

    public static final String STRUTS_FREEMARKER_WRAPPER_ALT_MAP = "struts.freemarker.wrapper.altMap";

    /** The name of the xwork converter implementation */
    public static final String STRUTS_XWORKCONVERTER = "struts.xworkConverter";

    public static final String STRUTS_ALWAYS_SELECT_FULL_NAMESPACE = "struts.mapper.alwaysSelectFullNamespace";

    /** XWork default text provider */
    public static final String STRUTS_XWORKTEXTPROVIDER = "struts.xworkTextProvider";

    /** The name of the parameter to create when mapping an id (used by some action mappers) */
	public static final String STRUTS_ID_PARAMETER_NAME = "struts.mapper.idParameterName";
	
	/** The name of the parameter to determine whether static method access will be allowed in OGNL expressions or not */
	public static final String STRUTS_ALLOW_STATIC_METHOD_ACCESS = "struts.ognl.allowStaticMethodAccess";

	/** The com.opensymphony.xwork2.validator.ActionValidatorManager 的实现类 */
    public static final String STRUTS_ACTIONVALIDATORMANAGER = "struts.actionValidatorManager";

    /** The {@link com.opensymphony.xwork2.util.ValueStackFactory} 的实现类 */
    public static final String STRUTS_VALUESTACKFACTORY = "struts.valueStackFactory";

    /** The {@link com.opensymphony.xwork2.util.reflection.ReflectionProvider} 的实现类 */
    public static final String STRUTS_REFLECTIONPROVIDER = "struts.reflectionProvider";

    /** The {@link com.opensymphony.xwork2.util.reflection.ReflectionContextFactory} 的实现类 */
    public static final String STRUTS_REFLECTIONCONTEXTFACTORY = "struts.reflectionContextFactory";
    
    /** The {@link com.opensymphony.xwork2.util.PatternMatcher} 的实现类 */
    public static final String STRUTS_PATTERNMATCHER = "struts.patternMatcher";

    /** The {@link org.apache.struts2.dispatcher.StaticContentLoader} 的实现类 */
    public static final String STRUTS_STATIC_CONTENT_LOADER = "struts.staticContentLoader";

    /** The {@link com.opensymphony.xwork2.UnknownHandlerManager} 的实现类 */
    public static final String STRUTS_UNKNOWN_HANDLER_MANAGER = "struts.unknownHandlerManager";

    /** Throw RuntimeException when a property is not found, or the evaluation of the espression fails*/
    public static final String STRUTS_EL_THROW_EXCEPTION = "struts.el.throwExceptionOnFailure";

    /** Logs properties that are not found (very verbose) **/
    public static final String STRUTS_LOG_MISSING_PROPERTIES = "struts.ognl.logMissingProperties";

    /** Enables caching of parsed OGNL expressions **/
    public static final String STRUTS_ENABLE_OGNL_EXPRESSION_CACHE = "struts.ognl.enableExpressionCache";
}
