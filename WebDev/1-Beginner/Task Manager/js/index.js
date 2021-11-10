function getAndupdate(){
    add= document.getElementById("add");
add.addEventListener("click", () => {
    console.log("Updating List...");
    tit=document.getElementById('title').value;
    desc=document.getElementById('description').value;
    if (localStorage.getItem('itemJson')==null){
        itemJsonArray = []; //
        itemJsonArray.push([tit,desc]);
        localStorage.setItem('itemsJson', JSON.stringify(itemJsonArray)); 
    }
    else{
        itemJsonArrayStr=localStorage.getItem('itemsJson')
        itemJsonArray=JSON.parse(itemJsonArrayStr);
        itemJsonArray.push([tit,desc]);
        localStorage.setItem('itemsJson', JSON.stringify(itemJsonArray));
    }
    update()
    let tablebody=document.getElementById("tablebody")
    let str="";
    itemJsonArray.forEach((element, index) => {
        str += `
        <tr>
            <td scope="row">${index+1}</td>
            <td>${element[0]}</td>
            <td>${element[1]}</td>
            <td><button type="button" class="btn btn-sm btn-primary" onclick="deleted($(index))" data-toggle="button" aria-pressed="false" autocomplete="off">Delete</button></td>
        </tr>`;

        
    });
    tablebody.innerHTML=str;
})

}
        add=document.getElementById("add");
        add.addEventListener("click",getAndupdate);
        getAndupdate();
        function deleted(itemIndex){
            console.log("deleted",itemIndex);
            itemJsonArrayStr=localStorage.getItem("itemJson")
            itemJsonArray=JSON.parse(itemJsonArray);
            ///delete
            itemJsonArray.splice(itemIndex,1);
            localstorage.setItem("itemJson", JSON.stringify(itemJsonArray));
            update();

        }
