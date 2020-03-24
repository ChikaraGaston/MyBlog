package com.pzr.service;

import com.pzr.NotFoundException;
import com.pzr.bo.TypeOrTagBlog;
import com.pzr.dao.TypeRepository;
import com.pzr.po.Type;
import com.pzr.util.EntityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService{

    @Autowired
    private TypeRepository typeRepository;

    @Transactional
    @Override
    public Type saveType(Type type) {
        return typeRepository.save(type);
    }

    @Transactional
    @Override
    public Type getType(Long id) {
        return typeRepository.getOne(id);
    }

    @Transactional
    @Override
    public Type getTypeByName(String name) {
        return typeRepository.findByName(name);
    }

    @Transactional
    @Override
    public Page<Type> listType(Pageable pageable) {
        return typeRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public List<Type> listType() {
        return typeRepository.findAll();
    }

//    @Transactional
////    @Override
////    public List<Type> listTypeTop(Integer size) {
////        Sort sort=Sort.by(Sort.Direction.DESC,"blogs.size");
////        Pageable pageable=PageRequest.of(0,size,sort);
//////        return typeRepository.findTop(pageable);
////    return null;
////    }

    @Override
    public List<TypeOrTagBlog> listTypeBlog() {
        List<Object[]> objects = typeRepository.findListTop();
        return EntityUtils.castEntity(objects,TypeOrTagBlog.class,new TypeOrTagBlog());
    }

    @Transactional
    @Override
    public Type updateType(Long id, Type type) {
        Type t = typeRepository.getOne(id);
        if (t == null) {
            throw new NotFoundException("不存在该类型");
        }
        BeanUtils.copyProperties(type,t);
        return typeRepository.save(t);
    }

    @Transactional
    @Override
    public void deleteType(Long id) {
        typeRepository.deleteById(id);
    }
}
