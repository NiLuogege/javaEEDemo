package com.niluogege.myspringboot.utils.generator;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.VisitableElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Mybatis插件
 */
public class LombokPlugin extends PluginAdapter {

    @Override
    public boolean validate(List<String> list) {
        return true;
    }

    /**
     * 为实体添加lombok的注解
     *
     * @param topLevelClass
     * @param introspectedTable
     * @return
     */
    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        //添加domain的import
        topLevelClass.addImportedType("lombok.Data");
        topLevelClass.addImportedType("lombok.Builder");
        topLevelClass.addImportedType("lombok.NoArgsConstructor");
        topLevelClass.addImportedType("lombok.AllArgsConstructor");

        //添加domain的注解
        topLevelClass.addAnnotation("@Data");
        topLevelClass.addAnnotation("@Builder");
        topLevelClass.addAnnotation("@NoArgsConstructor");
        topLevelClass.addAnnotation("@AllArgsConstructor");

        //添加domain的注释
        topLevelClass.addJavaDocLine("/**");
        topLevelClass.addJavaDocLine("* Model Auto Created For Table " + introspectedTable.getFullyQualifiedTableNameAtRuntime());
        topLevelClass.addJavaDocLine("*/");

        return true;
    }

    /**
     * 为实体类字段添加注释
     *
     * @param field
     * @param topLevelClass
     * @param introspectedColumn
     * @param introspectedTable
     * @param modelClassType
     * @return
     */
    @Override
    public boolean modelFieldGenerated(Field field, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        String classAnnotation = "@ApiModel(value=\"" + topLevelClass.getType().getShortName() + "\")";
        if (!topLevelClass.getAnnotations().contains(classAnnotation)) {
            topLevelClass.addAnnotation(classAnnotation);
        }
        String apiModelAnnotationPackage = this.properties.getProperty("apiModelAnnotationPackage");
        String apiModelPropertyAnnotationPackage = this.properties.getProperty("apiModelPropertyAnnotationPackage");
        if (null == apiModelAnnotationPackage) {
            apiModelAnnotationPackage = "io.swagger.annotations.ApiModel";
        }
        if (null == apiModelPropertyAnnotationPackage) {
            apiModelPropertyAnnotationPackage = "io.swagger.annotations.ApiModelProperty";
        }
        topLevelClass.addImportedType(apiModelAnnotationPackage);
        topLevelClass.addImportedType(apiModelPropertyAnnotationPackage);
        field.addAnnotation("@ApiModelProperty(value=\"" + introspectedColumn.getRemarks() +
                "\",name=\"" + introspectedColumn.getJavaProperty() + "\",notes=\"" + introspectedColumn.getRemarks() +
                "\")");
        return super.modelFieldGenerated(field, topLevelClass, introspectedColumn, introspectedTable, modelClassType);
    }

    /**
     * mapper.xml的注释
     *
     * @param interfaze
     * @param introspectedTable
     * @return
     */
    @Override
    public boolean clientGenerated(Interface interfaze, IntrospectedTable introspectedTable) {
        //Mapper文件的注释
        interfaze.addJavaDocLine("/**");
        interfaze.addJavaDocLine("* Mapper Auto Created For Table " + introspectedTable.getFullyQualifiedTableNameAtRuntime());
        interfaze.addJavaDocLine("*/");
        return true;
    }

    @Override
    public boolean clientSelectByPrimaryKeyMethodGenerated(Method method,
                                                           Interface interfaze, IntrospectedTable introspectedTable) {
        genMethodComment(method, "根据主键查询");
        return true;
    }

    @Override
    public boolean clientInsertMethodGenerated(Method method, Interface interfaze,
                                               IntrospectedTable introspectedTable) {
        genMethodComment(method, "插入全部字段");
        return true;
    }

    @Override
    public boolean clientInsertSelectiveMethodGenerated(Method method,
                                                        Interface interfaze, IntrospectedTable introspectedTable) {
        genMethodComment(method, "插入选择的字段");
        return true;
    }


    /**
     * 只能附加在自动生成的sqlMap后面,并不能直接替换自动生成的sqlMap
     */
    @Override
    public boolean sqlMapInsertElementGenerated(XmlElement element,
                                                IntrospectedTable introspectedTable) {
        return true;
    }

    /**
     * 只能附加在自动生成的sqlMap后面,并不能直接替换自动生成的sqlMap
     */
    @Override
    public boolean sqlMapInsertSelectiveElementGenerated(XmlElement element,
                                                         IntrospectedTable introspectedTable) {
        return true;
    }

    @Override
    public boolean clientUpdateByPrimaryKeySelectiveMethodGenerated(Method method,
                                                                    Interface interfaze, IntrospectedTable introspectedTable) {
        genMethodComment(method, "根据主键更新选择的字段");
        return true;
    }

    @Override
    public boolean clientUpdateByPrimaryKeyWithBLOBsMethodGenerated(Method method,
                                                                    Interface interfaze, IntrospectedTable introspectedTable) {
        genMethodComment(method, "根据主键更新全部字段");
        return true;
    }

    @Override
    public boolean clientUpdateByPrimaryKeyWithoutBLOBsMethodGenerated(
            Method method, Interface interfaze,
            IntrospectedTable introspectedTable) {
        genMethodComment(method, "根据主键更新全部字段");
        return true;
    }

    @Override
    public boolean clientDeleteByPrimaryKeyMethodGenerated(Method method,
                                                           Interface interfaze, IntrospectedTable introspectedTable) {
        genMethodComment(method, "根据主键删除记录");
        return true;
    }

    @Override
    public boolean modelSetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        //不生成getter
        return false;
    }

    @Override
    public boolean modelGetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        //不生成setter
        return false;
    }

    /**
     * 生成方法注释
     */
    private void genMethodComment(Method method, String comment) {
        method.addJavaDocLine("/**");
        method.addJavaDocLine(" * " + comment);
        method.addJavaDocLine(" */");
    }

    /**
     * 获取当前日期
     */
    protected String getDateString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.now().format(formatter);
    }

    /**
     * 去掉sqlMap中变量的jdbcType
     */
    private void removeJdbcTypefromXmlElement(XmlElement element) {
        List<VisitableElement> visitableElements = element.getElements();
        VisitableElement visitableElement;
        for (int i = 0; i < visitableElements.size(); i++) {
            visitableElement = visitableElements.get(i);
            if (visitableElement instanceof XmlElement) {
                removeJdbcTypefromXmlElement((XmlElement) visitableElement);
            } else if (visitableElement instanceof TextElement) {
                String content = ((TextElement) visitableElement).getContent();
                if (content.contains("jdbcType")) {
                    TextElement textElement = new TextElement(content.replaceAll(",jdbcType=\\w+}", "}"));
                    visitableElements.set(i, textElement);
                }
            }
        }
    }
}