#include <jni.h>
#include <string>
#include "art_method.h"
extern "C"
JNIEXPORT void JNICALL
Java_com_example_administrator_fixframwork_DexManager_replaceNative(JNIEnv *env, jclass type,
                                                                    jobject wrongMethod,
                                                                    jobject rightMethod) {
    art::mirror::ArtMethod* wrong= (art::mirror::ArtMethod *) env->FromReflectedMethod(wrongMethod);
    art::mirror::ArtMethod* right= (art::mirror::ArtMethod *) env->FromReflectedMethod(rightMethod);
    wrong->declaring_class_=right->declaring_class_;
    wrong->dex_code_item_offset_=right->dex_code_item_offset_;
    wrong->method_index_=right->method_index_;
    wrong->dex_method_index_=right->dex_method_index_;
}
