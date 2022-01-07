let likeIcon = (id) =>{
    let iconNode = document.getElementById(id);
    // iconNode.removeAttribute("class")
    // let newclass = iconNode.createAttribute("class")
    // newclass.nodeValue = "fa fa-thumbs-up"
    // iconNode.setAttribute(newclass)
    // iconNode.innerHTML = 501
    iconNode.className = "fa fa-thumbs-up icons"
}
let unlikeIcon = (id) =>{
    if(!liked){
        let iconNode = document.getElementById(id);
        iconNode.className = "fa fa-thumbs-o-up icons"
    }
    
    // iconNode.removeAttribute("class")
    // let newclass = iconNode.createAttribute("class")
    // newclass.nodeValue = "fa fa-thumbs-up"
    // iconNode.setAttribute(newclass)
    // iconNode.innerHTML = 501
    
}

let savedIcon = (id) =>{
    let iconNode = document.getElementById(id);
    iconNode.className = "fa fa-bookmark icons"
}
let unsavedIcon = (id) =>{
    if(!saved){
        let iconNode = document.getElementById(id);
        iconNode.className = "fa fa-bookmark-o icons"
    }
}