#!/usr/bin/env node
/* @Author Pallab Rath (pallab.rath@gmail.com) */

console.log("********************************************************");
console.log("Upload static website to Oracle OCI Object Storage");
console.log("********************************************************");
const os = require("oci-objectstorage");
const common = require("oci-common");
const fs = require("fs");
const { config } = require("process");
const walkSync = require('walk-sync');
const mime = require('mime-types')
let uploadConfig = require('./upload-config.json');
const provider = new common.ConfigFileAuthenticationDetailsProvider(
    uploadConfig.configurationFilePath,
    uploadConfig.configProfile
);

if (uploadConfig.bucketName == undefined || uploadConfig.bucketName == null || uploadConfig.bucketName == '')
{
    console.error("bucketName is missing in pload-config.json");
}
const allFiles = walkSync(uploadConfig.webdir, { directories: false });
const client = new os.ObjectStorageClient({
    authenticationDetailsProvider: provider
});

(async () => {
    try {
      console.log("Getting the namespace...");
      const request = {};
      const response = await client.getNamespace(request);
      const namespace = response.value;
  
      console.log("Creating the source bucket.");
      const bucketDetails = {
        name: uploadConfig.bucketName,
        compartmentId: uploadConfig.comaprtmentOCId,
        publicAccessType: "ObjectReadWithoutList"
      };
      const createBucketRequest = {
        namespaceName: namespace,
        createBucketDetails: bucketDetails
      };
      const createBucketResponse = await client.createBucket(createBucketRequest);
      console.log("Create Bucket executed successfully = "+createBucketResponse.bucket.name);
      var uploadCount = 0;
      for (let fileName of allFiles)
      {
        // Create stream to upload
        let fp = uploadConfig.webdir + "/" + fileName
        let stats = fs.statSync(fp);
        let objectData = fs.createReadStream(fp);
        console.log("Adding object to the Bucket = " + fileName);
        let putObjectRequest = {
          namespaceName: namespace,
          bucketName: uploadConfig.bucketName,
          putObjectBody: objectData,
          objectName: fileName,
          contentLength: stats.size,
          contentType: mime.lookup(fp)
        };
        let putObjectResponse = await client.putObject(putObjectRequest);
        console.log("Put Object executed successfully" + fileName);
        uploadCount ++;
      }
      console.log("Get Object executed successfully.");
      console.log("******************************");
      console.log("Total file uploaded ="+uploadCount);
      var indexUrl = client._endpoint + "/n/" + namespace + "/b/" + uploadConfig.bucketName + "/o/" + uploadConfig.index;
      console.log("Index file name = " + uploadConfig.index + " url = "+ indexUrl);
    } catch (error) {
      console.log("Error in uploading " + error);
    }
  })();