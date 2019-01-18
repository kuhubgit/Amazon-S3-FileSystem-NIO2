package com.upplication;

import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;

public class FilesOperationTest {

    private final String s3EndpointUrl = "s3://s3-ap-northeast-1.amazonaws.com/";
    private final String bucketName = "";

    @Test
    public void downloadAndCopy() throws IOException {
        String testFilePath = "/upload/hoge.png";
        Map<String, String> env = new HashMap<>();
        env.put(com.upplication.s3fs.AmazonS3Factory.ACCESS_KEY, "");
        env.put(com.upplication.s3fs.AmazonS3Factory.SECRET_KEY, "");

        FileSystem fileSystem = FileSystems.newFileSystem(
                URI.create(s3EndpointUrl), env, Thread.currentThread().getContextClassLoader());
        Path s3Path = fileSystem.getPath("/" + bucketName, testFilePath);
        Path local = Files
                .createTempFile(Paths.get("src/test/resources/"), "my", ".png");
        Files.copy(s3Path, Files.newOutputStream(local, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING));
    }
}
