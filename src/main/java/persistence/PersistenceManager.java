package persistence;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;
import model.Task;

import java.io.*;

public class PersistenceManager {
    private final static String ENDPOINT = "https://ipassyouri.blob.core.windows.net/";
    private final static String SASTOKEN = "?sv=2021-06-08&ss=bfqt&srt=sco&sp=rwdlacupitfx&se=2022-06-16T19:02:23Z&st=2022-06-16T11:02:23Z&spr=https&sig=BeVelKHYIHelytNkHf2R7qGRi6iNAJAcVsO94oZUt98%3D";
    private final static String CONATAINER = "ipasscontainer";

    private static BlobContainerClient blobContainer = new BlobContainerClientBuilder()
            .endpoint(ENDPOINT)
            .sasToken(SASTOKEN)
            .containerName(CONATAINER)
            .buildClient();
    public static void loadFromAzure() throws IOException, ClassNotFoundException {
        if (blobContainer.exists()){
            BlobClient blob = blobContainer.getBlobClient("ipassyouri");

            if(blob.exists()){
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                blob.download(baos);

                ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
                ObjectInputStream ois = new ObjectInputStream(bais);

                Task task = (Task) ois.readObject();
            }
        }
    }

//    public static void saveToAzure() throws IOException {
//        if (!blobContainer.exists()){
//            blobContainer.create();
//        }
//        if (blobContainer.exists()) {
//            BlobClient blob = blobContainer.getBlobClient("worldblob");
//            World world = World.getWorld();
//
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            ObjectOutputStream oos = new ObjectOutputStream(baos);
//            oos.writeObject(world);
//
//            byte[] bytez = baos.toByteArray();
//
//            ByteArrayInputStream bais = new ByteArrayInputStream(bytez);
//            blob.upload(bais, bytez.length, true);
//        }
}
