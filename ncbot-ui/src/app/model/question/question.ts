import {Option} from "../option/option";

export class Question {
  id:number;
  content:string;
  options:Option[];
  answer:string;
}
