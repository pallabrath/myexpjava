# Hosting a static website in OCI object store
 We can use OCI Object (https://docs.cloud.oracle.com/en-us/iaas/Content/Object/Concepts/objectstorageoverview.htm) to host static website. Object store allow to   store all sorts of files, So we can use it to store necessary files(html, js, css, images and media)required for a typical static website.
 As object store scales pretty well and is very cost effective. We can also used its alwasys free version (https://docs.cloud.oracle.com/en-us/iaas/Content/FreeTier/resourceref.htm)
 
This tool will create a public bucket with publicAccessType = ObjectReadWithoutList and upload your static website to the object store. To keep the hyperlink and other links intact, this will rename the object name based on the relative path. For example if we have page1.html inside pages folder, this tool will upload it as object name pages/page1.html.
## Usage
### Prerequisite
This tool need node and npm.
https://nodejs.org/en/
https://docs.npmjs.com/downloading-and-installing-node-js-and-npm

### Inputs
Required inputs for this tool need to be configure in **_upload-config.json_**.
```JavaScript
{
    "webdir" : "/Users/pallab/mylab/oci-os-static-web",   # This is the path of the static web need to be uploaded
    "index" : "index.html",                               # Its the index/home page of your website 
    "configurationFilePath" : "~/.oci/config",            # OCI credential configuration
    "configProfile" : "DEFAULT",                          # OCI credentail config profile 
    "comaprtmentOCId" : "ocid1.compartment.oc1......",    # OCI compartment OCID where we want to upload
    "bucketName" : "myexpdemo"                            # bucket name to be created

}
```
Details how to create OCI Credential configuration (https://docs.cloud.oracle.com/en-us/iaas/Content/API/SDKDocs/typescriptsdkgettingstarted.htm#Getting_Started)

### Run
To run the upload util
```node
node upload-util.js 
```

![upload tool example screen](https://github.com/pallabrath/myexpjava/blob/master/images/upload-util-screen.png)
