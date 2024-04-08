import { User } from "src/app/interfaces/user.interface";
import { Topic } from "../../topics/interfaces/topic.interface";

export interface NewPostRequest {
    // TODO : voir si topic objet ou juste id ?
    // topic: Topic;
    topic: string;
    title: string;
    content: string;
    //TODO : voir si je rajoute ici le user ou dans le back ?
    // auteur?: User;
}
