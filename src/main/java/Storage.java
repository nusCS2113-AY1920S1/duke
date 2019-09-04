import java.io.*;
import java.net.URL;

class Storage {
    private File f;
    private String input = "";
    Storage(String filePath){
        URL path = Duke.class.getResource(filePath);
        this.f = new File(path.getFile());
        /*encoding is ANSI */
    }
    String load(){
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(f))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                this.input += line + "\n";
                line = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            // exception handling
            System.out.println("file not found");
        } catch (IOException e) {
            // exception handling
            System.out.println("I/O Issues");
        }
        return this.input;
    }
    void store(Tasklist list) throws DukeException {
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.f))) {
            for(int i = 0; i< list.size(); i++) {
                String fileContent = list.get(i).getType() + " | " +
                        (list.get(i).checkCompletion() ? "1": "0") + " | "+ list.get(i).getDescription();
                if(list.get(i).getType().matches("D|E"))
                {
                    fileContent += " | " + list.get(i).getDueDate();
                }
                bufferedWriter.write(fileContent);
                bufferedWriter.newLine();
            }
        } catch (IOException | DukeException e) {
            // exception handling
            throw new DukeException("Storage Attempt Failed");
        }
    }

}
