function validate() {
    var name = document.getElementById("name").value;
    if (name == null || name == "" || name == " ") {
        alert("Please enter Board Game name.");
        return false;
    }
    
    var numberofsold = document.getElementById("numberofsold").value;
    if (numberofsold == null || numberofsold == "" || (isNaN(numberofsold)) || numberofsold < 0) {
        alert("Please enter valid number of sold board games.");
        return false;
    }
    
    var price = document.getElementById("price").value;
    if (price == null || price == "" || (isNaN(price)) || price <= 0) {
        alert("Please enter valid price.");
        return false;
    }
 
  	return true;
  }