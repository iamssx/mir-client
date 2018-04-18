package com.example.demo.instrumentation;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;

import java.io.IOException;
import java.lang.instrument.ClassDefinition;
import java.lang.instrument.Instrumentation;

/**
 * Created by SSX on 2017/8/24.
 */

public class LoadAgent {

    public static void agentmain(String args, Instrumentation inst) throws IOException {
        Class[] classes = inst.getAllLoadedClasses();
        for (Class cls : classes) {
            System.out.println(cls.getName());
        }
        PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = pathMatchingResourcePatternResolver.getResources(args);
        CachingMetadataReaderFactory readerFactory = new CachingMetadataReaderFactory();
        for (Resource resource : resources) {

            MetadataReader metadataReader = readerFactory.getMetadataReader(resource);
//            new ClassDefinition(metadataReader.getClassMetadata().getClassName(),

//            AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
//            Set<String> annotationTypes = annotationMetadata.getAnnotationTypes();
//            ClassMetadata classMetadata = metadataReader.getClassMetadata();
        }
    }
}

