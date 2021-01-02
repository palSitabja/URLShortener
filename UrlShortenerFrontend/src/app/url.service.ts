import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class UrlService {

  constructor(private http:HttpClient) { }

  saveUrl(longUrl:string) : Observable<any>{
    return <Observable<any>> this.http.post("https://ti-nyurl.herokuapp.com/generate",longUrl,{responseType:'text'});
  }
}
