import {Option} from "../option/option";
import {ImageFile} from "../image-file";

export class Question {
  id:number;
  content:string;
  options:Option[];
  answer:string;
  weight:number;
  attachment?:ImageFile;
}
