import { User } from "src/app/interfaces/user.interface";

export interface NewPostRequest {
    topic: string;
    title: string;
    content: string;
    //TODO : voir si je rajoute ici le user ou dans le back ?
    // auteur?: User;
}
