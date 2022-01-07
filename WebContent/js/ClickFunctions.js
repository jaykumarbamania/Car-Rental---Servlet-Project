var liked = false;
var saved = false;

let likedFunction = (id)=>{
    let iconNode = document.getElementById(id);
    let likesCount = parseFloat(iconNode.innerHTML)
    console.log(typeof likesCount)
    this.c
    
    if(!liked){
        iconNode.className = "fa fa-thumbs-up icons";
        likesCount += 1;
        iconNode.innerHTML = " "+likesCount
        liked=true;
    }else{
        iconNode.className = "fa fa-thumbs-o-up icons";
        likesCount -= 1;
        iconNode.innerHTML = " "+likesCount
        liked=false;
    }
    // liked = !liked;
}

let savedFunction = (id)=>{
    let iconNode = document.getElementById(id);
    if(!saved){
        iconNode.className = "fa fa-bookmark icons";
        saved=true;
    }else{
        iconNode.className = "fa fa-bookmark-o icons";
        saved=false;
    }
}