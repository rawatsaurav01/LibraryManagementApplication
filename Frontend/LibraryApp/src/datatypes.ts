export interface signUpDataType{
    name:string,
    email:string,
    password:string
}

export interface loginDataType{
    email:string,
    password:string
}


export interface wishlistDataType{
    
    wishlistId:number,
    canonical_isbn:string,
    copyright:string,
    language:string,
    page_count:number,
    seriesName:string,
    summary:string,
    title:string,
    email:string
}

export interface bookDataType{
    canonical_isbn:string,
    copyright:string,
    language:string,
    page_count:number,
    seriesName:string,
    summary:string,
    title:string 
}

export interface booksArrayDataType{
 results: bookDataType[] |any;
}

export interface wishlistArrayDataType{
    body:wishlistArrayDataType[]|any;
    statusCode:string,
    statusCodeValue:number
}