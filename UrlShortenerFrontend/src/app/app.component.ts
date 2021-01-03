import { Component } from '@angular/core';
import { UrlService } from './url.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = '';
  longUrl:string=""
  shortUrl:string=""
  loading:boolean=true
  submit:boolean=false
  error:string
  constructor(private urlService:UrlService){}
  submitLongUrl(){
    this.submit=true
    this.loading=true
    this.shortUrl=""
    this.error=""
    this.urlService.saveUrl(this.longUrl).subscribe(
      success=>{this.shortUrl=success 
        //console.log(success);
        this.submit=false
        this.loading=false
      },
      error=>{this.error=error.message
      //console.log("Inside error"+error.message);
      this.submit=false
      this.loading=false
      }
    )
  }
  
}
