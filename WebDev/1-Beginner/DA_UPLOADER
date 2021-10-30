<!DOCTYPE html>
<html>

<body style="background-color:lavender;text-align: center;">

    <form action="" method="post" enctype="multipart/form-data">
        <fieldset>
            Class no:
            <select name="classnumber" id="classnumber">
  <option value="VL2021220104192">VL2021220104192</option>
  <option value="VL2021220104045">VL2021220104045</option>
</select>
            <br><br> Name: <input type="text" name="name">
            <br><br>Reg no: <input type="text" name="regno">
            <br><br> E-mail: <input type="text" name="email">

            <br><br>Select image to upload:
            <input type="file" name="fileToUpload" id="fileToUpload">
            <input type="submit" value="Upload File" name="submit">
    </form>
    </fieldset>
</body>

</html>
<?php
$target_dir = "uploads/";
$target_file = $target_dir . basename($_FILES["fileToUpload"]["name"]);
$uploadOk = 1;
$imageFileType = strtolower(pathinfo($target_file,PATHINFO_EXTENSION));


//check whether reg no present in list.txt 
$search = $_POST["regno"];

$stringz = "19BCE0001 19BCE0002 19BCE0003 19BCE0004 19BCE0005 19BCE0006 19BCE0757";
$stringz = explode(" ", $stringz); 

if(in_array($search, $stringz)){

$uploadOk=1;

} else {

echo $search . " does NOT belong to this class!";

$uploadOk=0;
}
?>



    <br>

    <html>

    <body style="color: red; background-color:yellow;">

        <?php
// Check if file already exists-- duplicate file
if (file_exists($target_file)) {
  echo "Sorry, file already exists.";
  $uploadOk = 0;
}

// Check file size
if ($_FILES["fileToUpload"]["size"] > 1000000) {
  echo "Sorry, your file is too large.";
  $uploadOk = 0;
}

// Allow certain file formats
if($imageFileType != "txt" && $imageFileType != "doc" && $imageFileType != "pdf") {
  echo "Sorry, only txt, doc, pdf files are allowed.";
  $uploadOk = 0;
}

// Check if $uploadOk is set to 0 then print error
if ($uploadOk == 0) {
  echo "Sorry, your file was not uploaded.";
// if everything is ok, try to upload file
} else {

?>

            Class No:
            <?php echo $_POST["classnumber"]; ?><br> Name:
            <?php $upp=$_POST["name"]; 
echo strtoupper($upp)?><br> Reg No:
            <?php echo $_POST["regno"]; ?><br> E-MAIL:
            <?php echo $_POST["email"]; ?>

            <?php

$username=$_POST["name"];
$useremail = $_POST["email"];
$present = strtok($useremail, '@');

if(strcasecmp($username, $present) == 0){
  $uploadOk=1;
  echo " Match - True";
}
else{
  echo " Match - False";
  $uploadOk=0;
}

?><br>

                <?php  
$date1 = "20.10.2021";  //duedate
$date2= date("d.m.Y");  //current date
if ($date1 < $date2) {
  echo "Sorry, after due date!";
} else {
  echo "Date of Submission: ".$date2." (Before due date)";
  echo "<br>";
  echo "<br>";
}

  if (move_uploaded_file($_FILES["fileToUpload"]["tmp_name"], $target_file)) {
    echo "The file ". htmlspecialchars( basename( $_FILES["fileToUpload"]["name"])). " is uploaded SUCCESFULLY";
  } else {
    echo "Sorry, there was an error uploading your file.";
  }
}
?><br>

    </body>

    </html>
