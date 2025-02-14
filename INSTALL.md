# vs code theme

`Ctrl + Shift + P` then

```shell
Gradient Theme : enable
```

# minio

```shell
mkdir -p ${HOME}/minio/data

docker run \
   --user $(id -u):$(id -g) \
   --name minio1 \
   -e "MINIO_ROOT_USER=root" \
   -e "MINIO_ROOT_PASSWORD=root" \
   -v ${HOME}/minio/data:/data \
   quay.io/minio/minio server /data --console-address ":9001"
```

```xml
<!--     对象存储 Minio 依赖     -->
<dependency>
    <groupId>io.minio</groupId>
    <artifactId>minio</artifactId>
    <version>8.3.9</version>
</dependency>
```

```shell
	public static void main(String[] args) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
		MinioClient client = MinioClient.builder()
			.endpoint("http://localhost:9000")  //对象存储服务地址
			.credentials("mcdd1024", "mcdd1024@pwd")   //账户直接使用管理员
			.build();
		client.removeObject(RemoveObjectArgs.builder()
			.bucket("dev")
			.object("back.png")
			.build());
	}
```
