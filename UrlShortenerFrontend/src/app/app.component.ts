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
  error:string
  constructor(private urlService:UrlService){}
  submitLongUrl(){
    this.urlService.saveUrl(this.longUrl).subscribe(
      success=>{this.shortUrl=success 
        console.log(success);
      },
      error=>{this.error=error.message
      console.log("Inside error"+error.message);
      
      }
    )
  }
  
}
