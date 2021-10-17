package com.cuit.generator;
import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.InnerClass;
import org.mybatis.generator.api.dom.java.InnerEnum;
import org.mybatis.generator.api.dom.java.JavaElement;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.config.PropertyRegistry;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.Set;
import static org.mybatis.generator.internal.util.StringUtility.isTrue;
/**
 * 生成注释配置类
 */
public class ShopCommentGenerator implements CommentGenerator {
    /**
     * properties属性，即配置在 commentGenerator 标签之内的 Property 标签
     */
    private Properties properties;
    /**
     * properties配置文件
     */
    private Properties systemPro;
    /*
     * 是否生成日期
     */
    private boolean suppressDate;
    /**
     * 是否生成注释
     */
    private boolean suppressAllComments;
    /**
     * 日期格式
     */
    private String currentDateStr;
    public ShopCommentGenerator() {
        super();
        properties = new Properties();
        systemPro = System.getProperties();
        suppressDate = false;
        suppressAllComments = false;
        currentDateStr =
                DateTimeFormatter.ofPattern("yyyy/MM/dd").format(LocalDateTime.now());
    }
    /**
     * 此方法返回格式化的日期字符串以包含在Javadoc标记中和XML注释。
     *
     * @return
     */
    protected String getDateString() {
        String result = null;
        if (!suppressDate) {
            result = currentDateStr;
        }
        return result;
    }
    /**
     * 从该配置中的任何属性添加此实例的属性CommentGenerator配置。
     *
     * @param properties
     */
    @Override
    public void addConfigurationProperties(Properties properties) {
        this.properties.putAll(properties);
        suppressDate =
                isTrue(properties.getProperty(PropertyRegistry.COMMENT_GENERATOR_SUPPRESS_DATE));
        suppressAllComments =
                isTrue(properties.getProperty(PropertyRegistry.COMMENT_GENERATOR_SUPPRESS_ALL_COMMENTS));
    }
    /**
     * 为字段添加注释
     *
     * @param field
     * @param introspectedTable
     * @param introspectedColumn
     */
    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable,
                                IntrospectedColumn introspectedColumn) {
        if (suppressAllComments)
            return;
        StringBuilder sb = new StringBuilder();
        field.addJavaDocLine("/**");
        sb.append(" * ");
        sb.append(introspectedColumn.getRemarks());
        field.addJavaDocLine(sb.toString().replace("\n", " "));
        field.addJavaDocLine(" */");
    }
    /**
     * Java 属性注释
     *
     * @param field
     * @param introspectedTable
     */
    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable) {
        if (suppressAllComments)
            return;
        StringBuilder sb = new StringBuilder();
        field.addJavaDocLine("/**");
        sb.append(" * ");
        sb.append(introspectedTable.getFullyQualifiedTable());
        field.addJavaDocLine(sb.toString().replace("\n", " "));
        field.addJavaDocLine(" */");
    }
    /**
     * 为模型类添加注释
     *
     * @param topLevelClass
     * @param introspectedTable
     */
    @Override
    public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable
            introspectedTable) {
        if (suppressAllComments) {
            return;
        }
        topLevelClass.addJavaDocLine("/**");
        topLevelClass.addJavaDocLine(" * @author zhoubin ");
        topLevelClass.addJavaDocLine(" * @since 1.0.0");
        topLevelClass.addJavaDocLine(" */");
    }
    /**
     * Java类的类注释
     *
     * @param innerClass
     * @param introspectedTable
     */
    @Override
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable)
    {
        if (suppressAllComments) {
            return;
        }
        // 获取表注释
        String remarks = introspectedTable.getRemarks();
        innerClass.addJavaDocLine("/**");
        innerClass.addJavaDocLine("/* "+remarks);
        innerClass.addJavaDocLine(" * @author zhoubin ");
        innerClass.addJavaDocLine(" * @since 1.0.0");
        innerClass.addJavaDocLine(" */");
    }
    /**
     * 为类添加注释
     *
     * @param innerClass
     * @param introspectedTable
     * @param b
     */
    @Override
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable,
                                boolean b) {
        // 获取表注释
        String remarks = introspectedTable.getRemarks();
        innerClass.addJavaDocLine("/**");
        innerClass.addJavaDocLine("/* "+remarks);
        innerClass.addJavaDocLine(" * @author zhoubin ");
        innerClass.addJavaDocLine(" * @since 1.0.0");
        innerClass.addJavaDocLine(" */");
    }
    /**
     * 为枚举添加注释
     *
     * @param innerEnum
     * @param introspectedTable
     */
    @Override
    public void addEnumComment(InnerEnum innerEnum, IntrospectedTable introspectedTable) {
        if (suppressAllComments)
            return;
        StringBuilder sb = new StringBuilder();
        innerEnum.addJavaDocLine("/**");
        sb.append(" * ");
        sb.append(introspectedTable.getFullyQualifiedTable());
        innerEnum.addJavaDocLine(sb.toString().replace("\n", " "));
        innerEnum.addJavaDocLine(" */");
    }
    /**
     * 给getter方法加注释
     *
     * @param method
     * @param introspectedTable
     * @param introspectedColumn
     */
    @Override
    public void addGetterComment(Method method, IntrospectedTable introspectedTable,
                                 IntrospectedColumn introspectedColumn) {
    }
    /**
     * 给setter方法加注释
     *
     * @param method
     * @param introspectedTable
     * @param introspectedColumn
     */
    @Override
    public void addSetterComment(Method method, IntrospectedTable introspectedTable,
                                 IntrospectedColumn introspectedColumn) {
    }

    /**
     * 普通方法的注释，这里主要是XXXMapper.java里面的接口方法的注释
     *
     * @param method
     * @param introspectedTable
     */
    @Override
    public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable)
    {
    }
    /**
     * 给Java文件加注释，这个注释是在文件的顶部，也就是package上面。
     *
     * @param compilationUnit
     */
    @Override
    public void addJavaFileComment(CompilationUnit compilationUnit) {
    }
    /**
     * Mybatis的Mapper.xml文件里面的注释
     *
     * @param xmlElement
     */
    @Override
    public void addComment(XmlElement xmlElement) {
    }
    /**
     * 此方法为其添加了自定义javadoc标签。
     *
     * @param javaElement
     * @param markAsDoNotDelete
     */
    protected void addJavadocTag(JavaElement javaElement, boolean markAsDoNotDelete) {
    }
    /**
     * 为调用此方法作为根元素的第一个子节点添加注释。
     *
     * @param xmlElement
     */
    @Override
    public void addRootComment(XmlElement xmlElement) {
    }
    @Override
    public void addGeneralMethodAnnotation(Method method, IntrospectedTable
            introspectedTable, Set<FullyQualifiedJavaType> set) {
    }

    @Override
    public void addGeneralMethodAnnotation(Method method, IntrospectedTable
            introspectedTable, IntrospectedColumn introspectedColumn, Set<FullyQualifiedJavaType> set)
    {
    }
    @Override
    public void addFieldAnnotation(Field field, IntrospectedTable introspectedTable,
                                   Set<FullyQualifiedJavaType> set) {
    }
    @Override
    public void addFieldAnnotation(Field field, IntrospectedTable introspectedTable,
                                   IntrospectedColumn introspectedColumn, Set<FullyQualifiedJavaType> set) {
    }
    @Override
    public void addClassAnnotation(InnerClass innerClass, IntrospectedTable
            introspectedTable, Set<FullyQualifiedJavaType> set) {
    }
}
