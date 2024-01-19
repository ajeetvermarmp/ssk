import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

public class S3ImageDisplay {

    public static void main(String[] args) {
        // Replace these values with your actual AWS credentials and S3 bucket details
        String awsAccessKeyId = "your-access-key-id";
        String awsSecretAccessKey = "your-secret-access-key";
        String bucketName = "colossalinfo-s.s3.ap-south-1.amazonaws.com";
        String objectKey = "banner/banner_image1.png";

        // Build the S3 client
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("https://s3.ap-south-1.amazonaws.com", "ap-south-1")) // Update with your region
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(awsAccessKeyId, awsSecretAccessKey)))
                .build();

        try {
            // Generate a pre-signed URL for the S3 object
            String objectUrl = s3Client.generatePresignedUrl(bucketName, objectKey, null).toString();

            // Output the HTML to display the image
            System.out.println("<html>");
            System.out.println("<head>");
            System.out.println("<title>SSN</title>");
            System.out.println("</head>");
            System.out.println("<body>");
            System.out.println("<h1>SSN Images</h1>");
            System.out.println("<img src=\"" + objectUrl + "\" alt=\"Banner Image\">");
            System.out.println("</body>");
            System.out.println("</html>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
