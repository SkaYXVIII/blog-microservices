import React, {useEffect} from "react";
import CommentCreate from "./CommentCreate";
import CommentList from "./CommentList";
import API from "../fetch/axiosSetup";
const PostList = ({posts, setPosts}) => {

    const fetchPosts = async () => {
        const res = await API.get('/query-service/query');
        setPosts(res.data);
    }
    useEffect(() => {
        fetchPosts();

    },// eslint-disable-next-line
     []);

   const renderedPosts = Object.values(posts).map(post => {
         return( <div
              className="card"
              style={{width: '30%', marginBottom: '20px'}}
              key={post.id}>
              <div className="card-body">
                <h3>{post.title}</h3>
                <CommentList comments={post.comments}/>
                <CommentCreate postId={post.id} fetchPosts={fetchPosts}/>
              </div>
         </div>
         );
    });


    return <div className="d-flex flex-row flex-wrap justify-content-between">
        {renderedPosts}
    </div>;
}

export default PostList;